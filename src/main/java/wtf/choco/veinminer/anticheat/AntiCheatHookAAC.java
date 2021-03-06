package wtf.choco.veinminer.anticheat;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import me.konsolas.aac.api.PlayerViolationEvent;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

/**
 * The default Advanced AntiCheat (AAC) hook implementation
 */
public final class AntiCheatHookAAC implements AntiCheatHook, Listener {

    private final Set<UUID> exempt = new HashSet<>();

    @Override
    public String getPluginName() {
        return "AAC";
    }

    @Override
    public void exempt(Player player) {
        this.exempt.add(player.getUniqueId());
    }

    @Override
    public void unexempt(Player player) {
        this.exempt.remove(player.getUniqueId());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private void onAACViolation(PlayerViolationEvent event) {
        if (!exempt.contains(event.getPlayer().getUniqueId())) {
            return;
        }

        event.setCancelled(true);
    }

}
