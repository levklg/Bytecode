public class TestLogging implements TestLoggingInterface{

   @Log
    public void calculation(int param1, int param2) {
        TestLoggingInterface myClass = Ioc.createMyClass(new TestLogging());
        myClass.calculation(param1, param2);

    }




}
