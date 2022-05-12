package the_menu;


public abstract class MessageService {

    public static void ShowDeniedMessage(){
        ClearConsole();
        String message = "\n\t\tsorry for that,you can't be in";
        System.out.println(message);
    }

    public static void ShowSuccessfulUserCreateMessage(){
        ClearConsole();
        String message = "\n\t\tcongrats. User created!";
        System.out.println(message);
    }

    public static void ShowFailedCreateMessage(){
        ClearConsole();
        String message = "\n\t\tsorry but there is something wrong. Please, try again running the program";
        System.out.println(message);
    }

    public static void ShowTryAgainMessage(){
        ClearConsole();
        System.out.print("\n\t\tplease Press:\n\t\t1) Try again or\n\t\t2) Exit.\n");
        System.out.print("\n\t\t");
    }

    public static void ClearConsole(){
        try{
            String operatingSystem = System.getProperty("os.name"); //Check the current operating system

            if(operatingSystem.contains("Linux") || operatingSystem.contains("iOS")){
                ProcessBuilder processBuilder = new ProcessBuilder("clear");
                Process process = processBuilder.start();
                process.waitFor();
            } else{
                ProcessBuilder processBuilder = new ProcessBuilder("cml", "/c", "cls");
                Process process = processBuilder.start();
                process.waitFor();
            }

        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void ShowSuccessfulUserDeletedMessage() {
        ClearConsole();
        String message = "\n\t\tthe operation is done";
        System.out.println(message);
    }

    public static void ShowFailedDeleteMessage() {
        ClearConsole();
        String message = "\n\t\tsorry but there is something wrong. Please, try again running the program";
        System.out.println(message);
    }

    public static void ShowSuccessfulUserUpdatedMessage() {
        ClearConsole();
        String message = "\n\t\tcongrats,User updated!";
        System.out.println(message);
    }

    public static void ShowSuccessfulMealCreateMessage() {
        ClearConsole();
        String message = "\n\t\tthe operation is done";
        System.out.println(message);
    }

    public static void ShowSuccessfulMealDeletedMessage() {
        ClearConsole();
        String message = "\n\t\tthe operation is done";
        System.out.println(message);
    }

    public static void ShowSuccessfulMealUpdatedMessage() {
        ClearConsole();
        String message = "\n\t\tcongrats,the operation is done";
        System.out.println(message);
    }

    public static void ShowFailedUpdateMessage() {
        ClearConsole();
        String message = "\n\t\tsorry but there is something wrong. Please, try again running the program";
        System.out.println(message);
    }

    public static void ShowSuccessfulReceiptDeletedMessage() {
        ClearConsole();
        String message = "\n\t\tgreat,the operation is done";
        System.out.println(message);
    }


    public static void ShowSuccessfulRecieptCreateMessage() {
        ClearConsole();
        String message = "\n\t\tgreat,the operation is done";
        System.out.println(message);
    }

    public static void EmptyMessage() {
        ClearConsole();
        String message = "\n\t\tNo item. It's empty!";
        System.out.println(message);
    }

    public static void ShowInvalidNumber() {
        ClearConsole();
        String message = "\n\t\tsorry Invalid input.try again, please!";
        System.out.println(message);
    }
}
