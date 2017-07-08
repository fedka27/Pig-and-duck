package app.meat.util.rx;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;


/**
 * @author e.matsyuk
 */
public class RxSchedulersTest extends RxSchedulersAbs {

    @Override
    public Scheduler getMainThreadScheduler() {
        return Schedulers.computation();
    }

    @Override
    public Scheduler getIOScheduler() {
        return Schedulers.computation();
    }

    @Override
    public Scheduler getComputationScheduler() {
        return Schedulers.computation();
    }

}
