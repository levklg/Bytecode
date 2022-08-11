import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;



class Ioc {

    private Ioc() {
    }

    static TestLoggingInterface createMyClass() {
        InvocationHandler handler = new DemoInvocationHandler(new TestLogging());
        return (TestLoggingInterface) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{TestLoggingInterface.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final TestLoggingInterface myClass;
        private boolean q = false;

        DemoInvocationHandler(TestLoggingInterface myClass) {
            this.myClass = myClass;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


            Class<?> clazz = this.myClass.getClass();
            Method[] methods =  clazz.getDeclaredMethods();

           for(Method m : methods){
                if(m.isAnnotationPresent(Log.class)){

                    String param = " ";
                    for(int i = 0; i < args.length; i++){
                        param = param + args[i];
                        if(i < (args.length - 1)) param = param + ", ";
                    }
                    System.out.println("executed method: calculation, param:" + param);
                }
            }


          if(q){
              q = true;
           return method.invoke(myClass, args);

          }else{

              return null;
          }

        }

        @Override
        public String toString() {
            return "DemoInvocationHandler{" +
                    "myClass=" + myClass +
                    '}';
        }
    }
}