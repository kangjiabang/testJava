package rxjava;

import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RxJavaTest {


    @Test
    public void testObservableDefer() {

        Observable.defer(() ->  {
                   return Observable.interval(1,TimeUnit.SECONDS);
                }
        ).take(10).subscribe(System.out::println);

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testObservableScan() {

        Observable.interval(1,TimeUnit.SECONDS).take(10).scan((t1,t2) -> {
            return t1 + t2;
        }).subscribe(new Observer() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                System.out.println("Result:" + o);
            }
        });

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testWindowTimeSpan() {

        Observable.interval(1, TimeUnit.SECONDS).take(10).window(3,TimeUnit.SECONDS)
                .subscribe(new Observer<Observable<Long>>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("------>onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("------>onError()" + e);
                    }

                    @Override
                    public void onNext(Observable<Long> longObservable) {

                        System.out.println("------->onNext()");
                        longObservable.subscribe(new Action1<Long>() {
                            @Override
                            public void call(Long integer) {
                                System.out.println("------>call():" + integer);
                            }
                        });
                    }
                });

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    @Test
    public void testWindowCount() {

        Observable.interval(1, TimeUnit.SECONDS).take(10).window(3)
                .subscribe(new Observer<Observable<Long>>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("------>onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("------>onError()" + e);
                    }

                    @Override
                    public void onNext(Observable<Long> longObservable) {

                        System.out.println("------->onNext()");
                        longObservable.subscribe(new Action1<Long>() {
                            @Override
                            public void call(Long integer) {
                                System.out.println("------>call():" + integer);
                            }
                        });
                    }
                });

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testWindowScan() {

        Observable<Observable<Integer>> window = Observable.just(1,2,3).window(1);

        /*window.scan((t1,t2) -> t1+t2).subscribe(new Observer<Observable<Long>>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("------>onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("------>onError()" + e);
                    }

                    @Override
                    public void onNext(Observable<Long> longObservable) {

                        System.out.println("------->onNext()");
                        longObservable.subscribe(new Action1<Long>() {
                            @Override
                            public void call(Long integer) {
                                System.out.println("------>call():" + integer);
                            }
                        });
                    }
                })*/;

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void testWindowCountSkip() {

        Observable.interval(1, TimeUnit.SECONDS).take(10).window(2,1)
                .subscribe(new Observer<Observable<Long>>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("------>onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("------>onError()" + e);
                    }

                    @Override
                    public void onNext(Observable<Long> longObservable) {

                        System.out.println("------->onNext()");
                        longObservable.subscribe(new Action1<Long>() {
                            @Override
                            public void call(Long integer) {
                                System.out.println("------>call():" + integer);
                            }
                        });
                    }
                });

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
