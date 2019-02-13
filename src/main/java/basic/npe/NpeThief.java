package basic.npe;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author：zeqi
 * @Date: Created in 16:34 15/5/18.
 * @Description:
 */
@Slf4j
public class NpeThief {

    private boolean isPrintDetail = false;
    private boolean isPrintDigest = false;

   // static Logger logger = Logger.getLogger(NpeThief.class.getName());


    public void callManyNPEInLoop() {
        //config  -XX:-OmitStackTraceInFastThrow  will disable jvm optimize
            for (int i = 0; i < 1000000; i++) {
                try {
                    ((Object)null).getClass();
                } catch (Exception e) {
                    // This will switch from 2 to 0 (indicating our problem is happening)
                    if (e.getStackTrace().length > 0 && !isPrintDetail) {
                        log.error("result",e);
                        System.out.println(e.getStackTrace().length);
                        isPrintDetail = true;
                    }
                    if (e.getStackTrace().length == 0 && !isPrintDigest) {
                        log.error("result",e);
                        System.out.println(e.getStackTrace().length);
                        isPrintDigest = true;
                    }
                    //System.out.println(e.getStackTrace().length);
                }
            }
        }
        public static void main(String ... args) {
            NpeThief thief = new NpeThief();
            thief.callManyNPEInLoop();
        }
}
