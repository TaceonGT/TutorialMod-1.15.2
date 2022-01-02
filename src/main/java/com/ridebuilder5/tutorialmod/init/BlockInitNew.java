package com.ridebuilder5.tutorialmod.init;

import com.ridebuilder5.tutorialmod.TutorialMod;
import com.ridebuilder5.tutorialmod.TutorialMod.TutorialItemGroup;
import com.ridebuilder5.tutorialmod.objects.blocks.ExampleChestBlock;
import com.ridebuilder5.tutorialmod.objects.blocks.ModPressurePlateBlock;
import com.ridebuilder5.tutorialmod.objects.blocks.ModWoodButtonBlock;
import com.ridebuilder5.tutorialmod.objects.items.SpecialItem;

import net.minecraft.block.Block;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.PressurePlateBlock.Sensitivity;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInitNew {

	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, TutorialMod.MOD_ID);
	
	public static final RegistryObject<Block> DEF_BLOCK = BLOCKS.register("def_block", () -> new Block(Block.Properties.create(Material.IRON)));
	
	public static final RegistryObject<Block> EXAMPLE_STAIRS = BLOCKS.register("example_stairs", () -> new StairsBlock(() -> BlockInit.example_block.getDefaultState(), Block.Properties.create(Material.SAND, MaterialColor.GOLD)));
	public static final RegistryObject<Block> EXAMPLE_FENCE = BLOCKS.register("example_fence", () -> new FenceBlock(Block.Properties.create(Material.SAND, MaterialColor.GOLD)));
	public static final RegistryObject<Block> EXAMPLE_BUTTON = BLOCKS.register("example_button", () -> new ModWoodButtonBlock(Block.Properties.create(Material.SAND, MaterialColor.GOLD)));
	public static final RegistryObject<Block> EXAMPLE_PRESSURE_PLATE = BLOCKS.register("example_pressure_plate", () -> new ModPressurePlateBlock(Sensitivity.EVERYTHING, Block.Properties.create(Material.SAND, MaterialColor.GOLD)));
	
	public static final RegistryObject<Block> EXAMPLE_CHEST = BLOCKS.register("example_chest", () -> new ExampleChestBlock(Block.Properties.from(BlockInit.example_block)));
}
