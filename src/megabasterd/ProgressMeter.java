package megabasterd;

import static java.lang.System.out;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;

public final class ProgressMeter implements Runnable, SecureNotifiable
{
    private final Transference _transference;
    private volatile boolean _exit;
    private final Object _secure_notify_lock;
    private boolean _notified=false;

    ProgressMeter(Transference transference)
    {
        _secure_notify_lock = new Object();
        _transference = transference;
        _exit = false;
    }
    
    public void setExit(boolean value)
    {
        _exit = value;
    }
    
    @Override
    public void secureNotify()
    {
        synchronized(_secure_notify_lock) {
      
            _notified = true;
            
            _secure_notify_lock.notify();
        }
    }
    
    @Override
    public void secureWait() {
        
        synchronized(_secure_notify_lock)
        {
            while(!_notified) {

                try {
                    _secure_notify_lock.wait();
                } catch (InterruptedException ex) {
                    getLogger(ProgressMeter.class.getName()).log(SEVERE, null, ex);
                }
            }
            
            _notified = false;
        }
    }
    
    @Override
    public void secureNotifyAll() {
        
        synchronized(_secure_notify_lock) {
            
            _notified = true;
      
            _secure_notify_lock.notifyAll();
        }
    }
  
    @Override
    public void run()
    {  
        out.println("ProgressMeter hello!");
    
        while(!_exit || !_transference.getPartialProgress().isEmpty())
        {
            Integer reads;
            
            while( (reads=_transference.getPartialProgress().poll()) !=null )
            {
                _transference.updateProgress(reads);
            }
            
            if(!_exit)
            {
                secureWait();
            }
        }

    }

}