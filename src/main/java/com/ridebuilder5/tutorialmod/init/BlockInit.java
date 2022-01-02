package com.ridebuilder5.tutorialmod.init;

import com.ridebuilder5.tutorialmod.TutorialMod;
import com.ridebuilder5.tutorialmod.TutorialMod.TutorialItemGroup;
import com.ridebuilder5.tutorialmod.objects.blocks.BlockQuarry;
import com.ridebuilder5.tutorialmod.objects.blocks.SpecialBlock;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@ObjectHolder(TutorialMod.MOD_ID)
@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Bus.MOD)
public class BlockInit {

	public static final Block example_block = null;
	public static final Block special_block = null;
	public static final Block quarry = null;
	
	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		event.getRegistry().register(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(3.0F, 3.0F).sound(SoundType.SAND)).setRegistryName("example_block"));
		event.getRegistry().register(new SpecialBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(2.0F, 10.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.GLASS).lightValue(4).slipperiness(1.2F).speedFactor(0.7F).noDrops()).setRegistryName("special_block"));
		event.getRegistry().register(new BlockQuarry(Block.Properties.create(Material.IRON)).setRegistryName("quarry"));
	}
	
	@SubscribeEvent
	public static void registerBlockItems(final RegistryEvent.Register<Item> event) {
		event.getRegistry().register(new BlockItem(example_block, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("example_block"));
		event.getRegistry().register(new BlockItem(special_block, new Item.Properties().group(TutorialItemGroup.instance)).setRegistryName("special_block"));
		event.getRegistry().register(new BlockItem(quarry, new Item.Properties().group(TutorialItemGroup.instance)).setRegistryName("quarry"));
	}
}
