package it.theparanker.conditions.commands;

/*
Copyright _theparanker_ (c) 2026

ALL RIGHTS RESERVED.
================================================================================================
1. **Proprietary Restriction**:
   This Software is the exclusive property of the author. The use, copy, modification, 
   or distribution of this code is strictly prohibited for anyone without an explicit, 
   certified written permission granted by the original author.

2. **Prohibited Use**:
   - Unauthorized commercial or non-commercial use is strictly forbidden.
   - Any unlicensed integration into proprietary or open-source software is prohibited.

3. **No Warranty**:
   The Software is provided “as is”, without warranty of any kind, express or implied.
=================================================================================================
By viewing or using this software, you agree to the terms above.
==================================================================================================
*/

import it.theparanker.conditions.ConditionsMain;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConditionCommand implements CommandExecutor, TabCompleter {

    private final ConditionsMain main;

    public ConditionCommand(ConditionsMain main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (!sender.hasPermission("conditions.admin.reload")) {
                    sender.sendMessage(ChatColor.RED + "You dont have permissions.");
                    return true;
                }

                main.reload();

                sender.sendMessage(ChatColor.GREEN + "Conditions reloaded!");
                return true;
            }
        }

        sender.sendMessage(ChatColor.RED + "Invalid usage: /" + label + " reload");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            List<String> completions = new ArrayList<>();
            List<String> subCommands = List.of("reload");

            StringUtil.copyPartialMatches(args[0], subCommands, completions);
            Collections.sort(completions);

            return completions;
        }

        return List.of();
    }
}