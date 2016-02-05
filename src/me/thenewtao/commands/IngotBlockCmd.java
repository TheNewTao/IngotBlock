package me.thenewtao.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.thenewtao.ingotblock.IngotBlockMain;

public class IngotBlockCmd implements CommandExecutor {

	private IngotBlockMain main;

	public IngotBlockCmd(IngotBlockMain main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player && sender != null) {
			Player p = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("ingot") && sender.hasPermission("ingotblock.ingot")) {
				if (main.getBlocks().containsKey(p.getItemInHand().getType())) {
					Material m = p.getItemInHand().getType();
					int amount = p.getItemInHand().getAmount();
					p.getInventory().addItem(new ItemStack(main.getBlocks().get(m), (amount * 9)));
					p.getInventory().setItemInHand(new ItemStack(Material.AIR, 1));
				} else if (p.getItemInHand().getType() == Material.QUARTZ_BLOCK) {
					Material m = p.getItemInHand().getType();
					int amount = p.getItemInHand().getAmount();
					p.getInventory().addItem(new ItemStack(Material.QUARTZ, (amount * 4)));
					p.getInventory().setItemInHand(new ItemStack(Material.AIR, 1));
				} else if (p.getItemInHand().getType() == Material.LAPIS_BLOCK) {
					Material m = p.getItemInHand().getType();
					int amount = p.getItemInHand().getAmount();
					ItemStack lapiz = new ItemStack(Material.INK_SACK, (amount * 9), (byte) 4);
					p.getInventory().addItem(lapiz);
					p.getInventory().setItemInHand(new ItemStack(Material.AIR, 1));
				} else if (p.getItemInHand().getType() == Material.GOLD_NUGGET) {
					Material m = p.getItemInHand().getType();
					int amount = p.getItemInHand().getAmount();
					if (amount >= 9) {
						int remainder = amount % 9;
						p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, (amount / 9)));
						p.getInventory().setItemInHand(new ItemStack(Material.AIR, 1));
						p.getInventory().addItem(new ItemStack(Material.GOLD_NUGGET, remainder));
					} else {
						sender.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "========================");
						sender.sendMessage(
								ChatColor.YELLOW + ChatColor.BOLD.toString() + "You don't have enough gold nuggets!");
						sender.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "========================");
					}
				} else {
					sender.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "========================");
					sender.sendMessage(
							ChatColor.YELLOW + ChatColor.BOLD.toString() + "You can't use this command on this item");
					sender.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "========================");
				}
			}
			if (cmd.getName().equalsIgnoreCase("block") && sender.hasPermission("ingotblock.block")) {
				ItemStack lapiz = new ItemStack(Material.INK_SACK, 1, (byte) 4);
				if (main.getIngots().containsKey(p.getItemInHand().getType())) {
					int amount = p.getItemInHand().getAmount();
					Material m = p.getItemInHand().getType();
					if (amount > 8) {
						int total = amount / 9;
						int remainder = amount % 9;
						p.getInventory().setItemInHand(new ItemStack(Material.AIR, 1));
						p.getInventory().addItem(new ItemStack(main.getIngots().get(m), total));
						p.getInventory().addItem(new ItemStack(m, remainder));
					} else {
						sender.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "========================");
						sender.sendMessage(
								ChatColor.YELLOW + ChatColor.BOLD.toString() + "You don't have enough items!");
						sender.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "========================");
					}
				} else if (p.getItemInHand().getType() == Material.QUARTZ) {
					int amount = p.getItemInHand().getAmount();
					Material m = p.getItemInHand().getType();
					if (amount > 4) {
						int total = amount / 4;
						int remainder = amount % 4;
						p.getInventory().setItemInHand(new ItemStack(Material.AIR, 1));
						p.getInventory().addItem(new ItemStack(main.getIngots().get(m), total));
						p.getInventory().addItem(new ItemStack(m, remainder));
					} else {
						sender.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "========================");
						sender.sendMessage(
								ChatColor.YELLOW + ChatColor.BOLD.toString() + "You don't have enough items!");
						sender.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "========================");
					}
				} else if (p.getItemInHand().getType() == lapiz.getType()) {
					int amount = p.getItemInHand().getAmount();
					Material m = p.getItemInHand().getType();
					if (amount > 8) {
						int total = amount / 9;
						int remainder = amount % 9;
						p.getInventory().setItemInHand(new ItemStack(Material.AIR, 1));
						p.getInventory().addItem(new ItemStack(Material.LAPIS_BLOCK, total));
						p.getInventory().addItem(new ItemStack(m, remainder, (byte) 4));
					} else {
						sender.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "========================");
						sender.sendMessage(
								ChatColor.YELLOW + ChatColor.BOLD.toString() + "You don't have enough items!");
						sender.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "========================");
					}
				} else {
					sender.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "========================");
					sender.sendMessage(ChatColor.YELLOW + ChatColor.BOLD.toString() + "You can't /block this!");
					sender.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "========================");
				}
			}

			if (cmd.getName().equalsIgnoreCase("tnt") && sender.hasPermission("ingotblock.tnt")) {
				if (p.getInventory().containsAtLeast(new ItemStack(Material.SAND), 4)
						&& p.getInventory().containsAtLeast(new ItemStack(Material.SULPHUR), 5)) {

					int totalGunpowder = 0;
					int totalSand = 0;

					ItemStack[] inv = p.getInventory().getContents();
					for (ItemStack i : inv) {
						if (i != null && i.getType() == Material.SULPHUR) {
							totalGunpowder += i.getAmount();
							p.getInventory().removeItem(i);
						}
						if (i != null && i.getType() == Material.SAND) {
							totalSand += i.getAmount();
							p.getInventory().removeItem(i);
						}
					}
					if (totalGunpowder < totalSand || totalGunpowder == totalSand) {
						int gunpowder = totalGunpowder / 5;
						p.getInventory().addItem(new ItemStack(Material.TNT, gunpowder));
						int remainder = totalGunpowder % 5;
						p.getInventory().addItem(new ItemStack(Material.SULPHUR, remainder));
						p.getInventory().addItem(new ItemStack(Material.SAND, totalSand - (gunpowder * 4)));
					}
					if (totalGunpowder > totalSand) {
						int sand = totalSand / 4;
						p.getInventory().addItem(new ItemStack(Material.TNT, sand));
						int remainder = totalSand % 4;
						p.getInventory().addItem(new ItemStack(Material.SAND, remainder));
						p.getInventory().addItem(new ItemStack(Material.SULPHUR, totalGunpowder - (sand * 5)));
					}
					if (totalGunpowder == totalSand) {

					}
				} else {
					sender.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "========================");
					sender.sendMessage(ChatColor.YELLOW + ChatColor.BOLD.toString() + "You don't have enough items!");
					sender.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "========================");
				}
			}

		}
		return true;
	}

}
