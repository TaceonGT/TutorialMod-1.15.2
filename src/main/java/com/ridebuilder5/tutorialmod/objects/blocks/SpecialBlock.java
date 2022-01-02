package com.ridebuilder5.tutorialmod.objects.blocks;

import java.util.stream.Stream;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class SpecialBlock extends Block {

	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	
	private static final VoxelShape SHAPE_N = Stream.of(
			Block.makeCuboidShape(7, 0, 7, 9, 2, 10),
			Block.makeCuboidShape(6, 2, 6, 10, 3, 11),
			Block.makeCuboidShape(6, 3, 10, 10, 7, 11),
			Block.makeCuboidShape(5, 4, 6, 6, 5, 10),
			Block.makeCuboidShape(10, 4, 6, 11, 5, 10)
			).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	private static final VoxelShape SHAPE_W = Stream.of(
			Block.makeCuboidShape(6, 0, 7, 9, 2, 9),
			Block.makeCuboidShape(5, 2, 6, 10, 3, 10),
			Block.makeCuboidShape(9, 3, 6, 10, 7, 10),
			Block.makeCuboidShape(5, 4, 10, 9, 5, 11),
			Block.makeCuboidShape(5, 4, 5, 9, 5, 6)
			).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	private static final VoxelShape SHAPE_S = Stream.of(
			Block.makeCuboidShape(7, 0, 6, 9, 2, 9),
			Block.makeCuboidShape(6, 2, 5, 10, 3, 10),
			Block.makeCuboidShape(6, 3, 5, 10, 7, 6),
			Block.makeCuboidShape(10, 4, 6, 11, 5, 10),
			Block.makeCuboidShape(5, 4, 6, 6, 5, 10)
			).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	private static final VoxelShape SHAPE_E = Stream.of(
			Block.makeCuboidShape(7, 0, 7, 10, 2, 9),
			Block.makeCuboidShape(6, 2, 6, 11, 3, 10),
			Block.makeCuboidShape(6, 3, 6, 7, 7, 10),
			Block.makeCuboidShape(7, 4, 5, 11, 5, 6),
			Block.makeCuboidShape(7, 4, 10, 11, 5, 11)
			).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	
	public SpecialBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch (state.get(FACING)) {
			case NORTH:
				return SHAPE_N;
			case SOUTH:
				return SHAPE_S;
			case EAST:
				return SHAPE_E;
			case WEST:
				return SHAPE_W;
			default:
				return SHAPE_N;
		}
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}
	
	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.with(FACING, rot.rotate(state.get(FACING)));
	}
	
	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.toRotation(state.get(FACING)));
	}
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult p_225533_6_) {
		if (!worldIn.isRemote) {
			ServerWorld serverWorld = (ServerWorld) worldIn;
			LightningBoltEntity entity = new LightningBoltEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), false);
			serverWorld.addLightningBolt(entity);
		}
		
		return ActionResultType.SUCCESS;
	}
}
