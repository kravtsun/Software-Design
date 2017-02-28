package commandCreator;

/**
 *
 */
public class CommandCreate {

    /**
     * Get command by name
     *
     * @param nameCommand name command
     * @return created command
     * @throws Exception command name not found
     */
    public static CommandCreatorFactory getCommand(String nameCommand) throws Exception {
        CommandCreatorFactory commandCreatorFactory;

        switch (nameCommand) {
            case "pwd":
                commandCreatorFactory = new PwdCreator();
                break;

            case "echo":
                commandCreatorFactory = new EchoCreator();
                break;

            case "cat":
                commandCreatorFactory = new CatCreator();
                break;

            case "wc":
                commandCreatorFactory = new WcCreator();
                break;

            case "exit":
                commandCreatorFactory = new ExitCreator();
                break;

            default:
                throw new Exception("command name not found");
        }
        return commandCreatorFactory;
    }

}
