package me.jinky.handlers;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import me.jinky.Cenix;
import me.jinky.util.MiniPlugin;

public class ExemptHandler extends MiniPlugin {

	public ExemptHandler(Cenix plugin) {
		super("Exemption Handler", plugin);
	}

	private static Map<Player, Long> EXEMPT = new HashMap<Player, Long>();
	private static Map<Player, Long> EXEMPT_BLOCK = new HashMap<Player, Long>();

	public boolean isExemptBlock(Player p) {
		if (EXEMPT_BLOCK.containsKey(p)) {
			if (System.currentTimeMillis() < EXEMPT_BLOCK.get(p)) {
				return true;
			} else {
				EXEMPT_BLOCK.remove(p);
				return false;
			}
		}
		return false;
	}

	public void removeExemptionBlock(Player p) {
		if (EXEMPT_BLOCK.containsKey(p))
			EXEMPT_BLOCK.remove(p);
	}

	public void addExemptionBlock(Player p, int ms) {
		if (isExempt(p))
			removeExemption(p);

		EXEMPT_BLOCK.put(p, System.currentTimeMillis() + ms);
	}

	public boolean isExempt(Player p) {
		if (EXEMPT.containsKey(p)) {
			if (System.currentTimeMillis() < EXEMPT.get(p) && !isExemptBlock(p)) {
				return true;
			} else {
				EXEMPT.remove(p);
				return false;
			}
		}
		return false;
	}

	public void removeExemption(Player p) {
		if (EXEMPT.containsKey(p))
			EXEMPT.remove(p);
	}

	public void addExemption(Player p, int ms) {
		if (isExemptBlock(p)) {
			return;
		}
		if (isExempt(p))
			removeExemption(p);

		EXEMPT.put(p, System.currentTimeMillis() + ms);
	}

}