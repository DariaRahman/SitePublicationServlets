package com.dariarahman.web.command;

import com.dariarahman.web.command.admin.AddPeriodicalCommand;
import com.dariarahman.web.command.admin.AdminPageCommand;
import com.dariarahman.web.command.admin.AdminProfileCommand;
import com.dariarahman.web.command.admin.BlockCommand;
import com.dariarahman.web.command.admin.DeletePeriodical;
import com.dariarahman.web.command.admin.EditPeriodical;
import com.dariarahman.web.command.admin.ShowUsersCommand;
import com.dariarahman.web.command.admin.UnblockCommand;
import com.dariarahman.web.command.client.ClientPageCommand;
import com.dariarahman.web.command.client.ClientProfileCommand;
import com.dariarahman.web.command.client.PaymentCommand;
import com.dariarahman.web.command.client.PaymentFormCommand;
import com.dariarahman.web.command.client.ShowSubscriptionsCommand;
import com.dariarahman.web.command.client.SubscribeCommand;
import com.dariarahman.web.command.client.TopUpBalanceCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
// Класс CommandContainer, который представляет контейнер для хранения и доступа к командам веб-приложения.
// Класс содержит статическое поле commands, представляющее карту (Map),
// где ключом является имя команды (строка), а значением - соответствующий объект команды.
public class CommandContainer {

    private static final Logger log = LogManager.getLogger(CommandContainer.class);

    private static final Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("login", new LoginCommand());
        commands.put("signup", new SignupCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("clientPage", new ClientPageCommand());
        commands.put("clientProfile", new ClientProfileCommand());
        commands.put("updateUser", new UpdateUserCommand());
        commands.put("adminPage", new AdminPageCommand());
        commands.put("adminProfile", new AdminProfileCommand());
        commands.put("subscribe", new SubscribeCommand());
        commands.put("showSubscriptions", new ShowSubscriptionsCommand());
        commands.put("mainPage", new MainPageCommand());
        commands.put("showUsers", new ShowUsersCommand());
        commands.put("payment", new PaymentCommand());
        commands.put("paymentForm", new PaymentFormCommand());
        commands.put("topUpBalance", new TopUpBalanceCommand());
        commands.put("block", new BlockCommand());
        commands.put("unblock", new UnblockCommand());
        commands.put("editPeriodical", new EditPeriodical());
        commands.put("addPeriodical", new AddPeriodicalCommand());
        commands.put("deletePeriodical", new DeletePeriodical());
        commands.put("noCommand", new DeletePeriodical());

        log.debug("Command container was successfully initialized");
        log.trace("Number of commands --> " + commands.size());
    }

    public static Command getCommand(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            log.warn("Command not found, commandName ==> " + commandName);
            return commands.get("noCommand");
        }

        return commands.get(commandName);
    }
}
