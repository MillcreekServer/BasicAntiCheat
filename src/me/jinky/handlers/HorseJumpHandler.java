package me.jinky.handlers;

import java.util.HashMap;

import org.bukkit.entity.AbstractHorse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.HorseJumpEvent;

import me.jinky.BAC;

public class HorseJumpHandler implements Listener {
  private static HashMap<AbstractHorse,Long> jumps;

  public static boolean horseIsJumping(AbstractHorse horse) {
    return jumps.containsKey(horse);
  }

  public HorseJumpHandler() {
    jumps = new HashMap<AbstractHorse, Long>();
  }

  @EventHandler
  public void onHorseJump(HorseJumpEvent ev) {
    long timestamp = System.currentTimeMillis();
    jumps.put(ev.getEntity(), timestamp);

    BAC.core.getServer().getScheduler().runTaskLaterAsynchronously(BAC.core, () -> {
      if (jumps.get(ev.getEntity()) == timestamp) {
        jumps.remove(ev.getEntity());
      }
    }, 20L);
  }
}