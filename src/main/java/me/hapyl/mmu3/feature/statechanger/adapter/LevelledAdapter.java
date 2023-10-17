package me.hapyl.mmu3.feature.statechanger.adapter;

import me.hapyl.mmu3.feature.statechanger.StateChangerGUI;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public abstract class LevelledAdapter<T extends BlockData> extends Adapter<T> implements IAdapter<T, Integer> {
    public LevelledAdapter(@Nonnull Class<T> clazz) {
        super(clazz);
    }

    public abstract int getLevel(T t);

    public int getMinLevel(T t) {
        return 0;
    }

    public abstract int getMaxLevel(T t);

    @Override
    public void update(@Nonnull StateChangerGUI gui, @Nonnull Player player, @Nonnull T blockData) {
        final int slot = getSlot();
        final int level = getLevel(blockData);
        final int minLevel = getMinLevel(blockData);
        final int maxLevel = getMaxLevel(blockData);

        gui.setLevelableItem(this, slot, level, maxLevel, getMaterial(), getName(), getDescription());
        gui.applyLevelable(slot, blockData, minLevel, level, maxLevel, apply());
    }
}
