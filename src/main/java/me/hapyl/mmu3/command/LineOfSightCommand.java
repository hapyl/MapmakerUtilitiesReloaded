package me.hapyl.mmu3.command;

import me.hapyl.mmu3.message.Message;
import me.hapyl.spigotutils.module.chat.Chat;
import me.hapyl.spigotutils.module.chat.LazyEvent;
import me.hapyl.spigotutils.module.command.SimplePlayerAdminCommand;
import me.hapyl.spigotutils.module.util.BukkitUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class LineOfSightCommand extends SimplePlayerAdminCommand {
    public LineOfSightCommand(String name) {
        super(name);
        setDescription("Displays information of block in line of sight.");
    }

    @Override
    protected void execute(Player player, String[] args) {
        final Block block = player.getTargetBlockExact(100);

        if (block == null) {
            Message.error(player, "No block in sight.");
            return;
        }

        final Material type = block.getType();
        final String blockData = block.getBlockData().getAsString();
        final Location location = BukkitUtils.centerLocation(block.getLocation());

        final String locationString = "%s %s %s".formatted(location.getX(), location.getBlockY(), location.getZ());
        final String locationStringCommas = "%s, %s, %s".formatted(location.getX(), location.getBlockY(), location.getZ());

        Message.info(player, "Looking at %s", Chat.capitalize(type), blockData);

        Message.clickHover(
                player,
                LazyEvent.copyToClipboard(locationString),
                LazyEvent.showText("&7Click to copy location."),
                " Location: %s &6&lCOPY",
                locationString
        );

        Message.clickHover(
                player,
                LazyEvent.copyToClipboard(locationStringCommas),
                LazyEvent.showText("&7Click to copy location with commas."),
                " Location: %s &6&lCOPY WITH COMMAS",
                locationStringCommas
        );

        if (blockData.contains("[")) {
            final String blockDataString = blockData.substring(blockData.lastIndexOf("["));
            Message.clickHover(
                    player,
                    LazyEvent.copyToClipboard(blockDataString),
                    LazyEvent.showText("&7Click to copy."),
                    " Data: %s &6&lCOPY",
                    blockDataString
            );
        }
    }
}
