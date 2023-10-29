package me.coopersully.rpgloot.rpgloot.listeners;

import me.coopersully.rpgloot.rpgloot.CoreUtils;
import me.coopersully.rpgloot.rpgloot.items.treasure_items.TreasureItemKeys;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;
import org.jetbrains.annotations.NotNull;

public class ArrowLand implements Listener {

    @EventHandler
    public void onShootBow(@NotNull ProjectileHitEvent event) {

        Projectile entity = event.getEntity();

        if (entity.getPersistentDataContainer().has(TreasureItemKeys.evokerBow)) {

            Entity hit = event.getHitEntity();
            World world = entity.getWorld();
            Location location;

            if (hit != null) {
                event.setCancelled(true);
                location = hit.getLocation();
                if (hit instanceof LivingEntity hitAlive) hitAlive.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 99));
            }
            else location = entity.getLocation();

            assert EntityType.EVOKER_FANGS.getEntityClass() != null;
            EvokerFangs fangs = (EvokerFangs) world.spawn(location, EntityType.EVOKER_FANGS.getEntityClass());
            ProjectileSource shooter = entity.getShooter();
            if (shooter instanceof LivingEntity shooterAlive) fangs.setOwner(shooterAlive);
            CoreUtils.killProjectile(event.getEntity());
        }

    }

}
