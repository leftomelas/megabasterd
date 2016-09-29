package megabasterd;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author tonikelope
 */
public interface Transference {
    
    int MIN_WORKERS = 1;
    int MAX_WORKERS = 10;
    int MAX_SIM_TRANSFERENCES=20;
    int WORKERS_DEFAULT = 4;
    int SIM_TRANSFERENCES_DEFAULT=2;
    boolean LIMIT_TRANSFERENCE_SPEED_DEFAULT=false;
    int MAX_TRANSFERENCE_SPEED_DEFAULT=1;
    
    void start();
   
    void stop();
    
    void pause();
    
    void restart();
    
    void close();
    
    boolean isPaused();
    
    boolean isStopped();
    
    void checkSlotsAndWorkers();
    
    ConcurrentLinkedQueue<Integer> getPartialProgress();
    
    long getProgress();
    
    void updateProgress(int reads);
    
    String getFile_name();
    
    long getFile_size();
    
    ProgressMeter getProgress_meter();
    
    SpeedMeter getSpeed_meter();
    
    MainPanel getMain_panel();
    
    TransferenceView getView();
}