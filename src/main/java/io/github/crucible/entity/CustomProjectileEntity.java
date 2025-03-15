package io.github.crucible.entity;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ItemInWorldManager;
import net.minecraft.world.WorldServer;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_7_R4.CraftServer;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.projectiles.BlockProjectileSource;
import org.bukkit.projectiles.ProjectileSource;

import java.util.UUID;

public class CustomProjectileEntity extends CraftCustomEntity implements Projectile {
    public static final GameProfile dropper = new GameProfile(UUID.nameUUIDFromBytes("[Dropper]".getBytes()), "[Dropper]");
    private ProjectileSource shooter = null;
    private boolean doesBounce;

    public CustomProjectileEntity(CraftServer server, Entity entity) {
        super(server, entity);
    }

    @Override
    public LivingEntity _INVALID_getShooter() {
        if (shooter instanceof LivingEntity) {
            return (LivingEntity) shooter;
        }
        if (shooter instanceof BlockProjectileSource) {
            Block block = ((BlockProjectileSource) shooter).getBlock();
            if (!(block.getWorld() instanceof WorldServer)) return null;
            int x = block.getX(), y = block.getY(), z = block.getZ();
            WorldServer ws = (WorldServer) block.getWorld();
            EntityPlayerMP fake_dropper = new EntityPlayerMP(MinecraftServer.getServer(), ws, dropper, new ItemInWorldManager(MinecraftServer.getServer().worldServerForDimension(0)));
            fake_dropper.posX = x;
            fake_dropper.posY = y;
            fake_dropper.posZ = z;
            CraftEntity ce = CraftEntity.getEntity(MinecraftServer.getServer().server, fake_dropper);
            if (ce instanceof LivingEntity) return (LivingEntity) ce;
            return null;
        }
        return null;
    }

    @Override
    public ProjectileSource getShooter() {
        return shooter;
    }

    @Override
    public void setShooter(ProjectileSource shooter) {
        this.shooter = shooter;
    }

    @Override
    public void _INVALID_setShooter(LivingEntity living) {
        if (living instanceof ProjectileSource) {
            this.shooter = living;
        }
    }

    @Override
    public boolean doesBounce() {
        return doesBounce;
    }

    @Override
    public void setBounce(boolean doesBounce) {
        this.doesBounce = doesBounce;
    }

    @Override
    public String toString() {
        return "CraftCustomProjectile";
    }
}

