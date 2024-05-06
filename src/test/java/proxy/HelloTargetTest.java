package proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class HelloTargetTest {
    @Test
    void helloTargetProxyTest() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloTarget.class);
        enhancer.setCallback(new BigLetterIntercepter());
        Object obj = enhancer.create();
        HelloTarget helloTarget = (HelloTarget) obj;
        String actualSayHello = helloTarget.sayHello("다희");
        String actualHi = helloTarget.sayHi("다희");
        String actualThankYou = helloTarget.sayThankYou("다희");

        Assertions.assertThat(actualSayHello).isEqualTo("HELLO 다희");
        Assertions.assertThat(actualHi).isEqualTo("HI 다희");
        Assertions.assertThat(actualThankYou).isEqualTo("THANK YOU 다희");
    }
}
