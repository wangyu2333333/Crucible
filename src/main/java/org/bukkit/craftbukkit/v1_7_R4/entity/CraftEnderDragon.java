package org.bukkit.craftbukkit.v1_7_R4.entity;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityDragonPart;
import org.bukkit.craftbukkit.v1_7_R4.CraftServer;
import org.bukkit.entity.ComplexEntityPart;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EntityType;

import java.util.Set;

public class CraftEnderDragon extends CraftComplexLivingEntity implements EnderDragon {
    public CraftEnderDragon(CraftServer server, EntityDragon entity) {
        super(server, entity);
    }

    public Set<ComplexEntityPart> getParts() {
        Builder<ComplexEntityPart> builder = ImmutableSet.builder();

        for (EntityDragonPart part : getHandle().dragonPartArray) {
            builder.add((ComplexEntityPart) part.getBukkitEntity());
        }

        return builder.build();
    }

    @Override
    public EntityDragon getHandle() {
        return (EntityDragon) entity;
    }

    @Override
    public String toString() {
        return "CraftEnderDragon";
    }

    public EntityType getType() {
        return EntityType.ENDER_DRAGON;
    }
}
