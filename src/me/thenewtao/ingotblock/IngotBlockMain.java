package me.thenewtao.ingotblock;

import java.util.HashMap;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import me.thenewtao.commands.IngotBlockCmd;

public class IngotBlockMain extends JavaPlugin {
	public HashMap<Material, Material> b = new HashMap<Material, Material>();
	public HashMap<Material, Material> i = new HashMap<Material, Material>();

	@Override
	public void onEnable() {
		
		this.getCommand("ingot").setExecutor(new IngotBlockCmd(this));
		this.getCommand("block").setExecutor(new IngotBlockCmd(this));
		this.getCommand("tnt").setExecutor(new IngotBlockCmd(this));
		
		b.put(Material.COAL_BLOCK, Material.COAL);
		b.put(Material.SLIME_BLOCK, Material.SLIME_BALL);
		b.put(Material.IRON_BLOCK, Material.IRON_INGOT);
		b.put(Material.GOLD_BLOCK, Material.GOLD_INGOT);
		b.put(Material.DIAMOND_BLOCK, Material.DIAMOND);
		b.put(Material.REDSTONE_BLOCK, Material.REDSTONE);
		b.put(Material.EMERALD_BLOCK, Material.EMERALD);

		i.put(Material.COAL, Material.COAL_BLOCK);
		i.put(Material.SLIME_BALL, Material.SLIME_BLOCK);
		i.put(Material.IRON_INGOT, Material.IRON_BLOCK);
		i.put(Material.GOLD_INGOT, Material.GOLD_BLOCK);
		i.put(Material.DIAMOND, Material.DIAMOND_BLOCK);
		i.put(Material.REDSTONE, Material.REDSTONE_BLOCK);
		i.put(Material.EMERALD, Material.EMERALD_BLOCK);

	}

}
