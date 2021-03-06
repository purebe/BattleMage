package purebe.battlemage.algorithms;

import java.util.Queue;

import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3i;

public final class Vectors {	
	final public static Vec3i UP = new Vec3i(0, 1, 0);
	final public static Vec3i DOWN = new Vec3i(0, -1, 0);
	final public static Vec3i EAST = new Vec3i(1, 0, 0);
	final public static Vec3i WEST = new Vec3i(-1, 0, 0);
	final public static Vec3i NORTH = new Vec3i(0, 0, 1);
	final public static Vec3i SOUTH = new Vec3i(0, 0, -1);
	
	final public static Vec3i NORTH_EAST = new Vec3i(1, 0, 1);
	final public static Vec3i NORTH_WEST = new Vec3i(-1, 0, 1);
	final public static Vec3i SOUTH_EAST = new Vec3i(1, 0, -1);
	final public static Vec3i SOUTH_WEST = new Vec3i(-1, 0, -1);
	
	final public static Vec3i UP_EAST = new Vec3i(1, 1, 0);
	final public static Vec3i UP_WEST = new Vec3i(-1, 1, 0);
	final public static Vec3i UP_NORTH = new Vec3i(0, 1, 1);
	final public static Vec3i UP_SOUTH = new Vec3i(0, 1, -1);
	final public static Vec3i DOWN_EAST = new Vec3i(1, -1, 0);
	final public static Vec3i DOWN_WEST = new Vec3i(-1, -1, 0);
	final public static Vec3i DOWN_NORTH = new Vec3i(0, -1, 1);
	final public static Vec3i DOWN_SOUTH = new Vec3i(0, -1, -1);
	
	final public static Vec3i UP_NORTH_EAST = new Vec3i(1, 1, 1);
	final public static Vec3i UP_NORTH_WEST = new Vec3i(-1, 1, 1);
	final public static Vec3i UP_SOUTH_EAST = new Vec3i(1, 1, -1);
	final public static Vec3i UP_SOUTH_WEST = new Vec3i(-1, 1, -1);
	final public static Vec3i DOWN_NORTH_EAST = new Vec3i(1, -1, 1);
	final public static Vec3i DOWN_NORTH_WEST = new Vec3i(-1, -1, 1);
	final public static Vec3i DOWN_SOUTH_EAST = new Vec3i(1, -1, -1);
	final public static Vec3i DOWN_SOUTH_WEST = new Vec3i(-1, -1, -1);
	
	public static Vec3i add(Vec3i one, Vec3i two) {
		return new Vec3i(one.getX() + two.getX(), one.getY() + two.getY(), one.getZ() + two.getZ());
	}
	
	public static void Enqueue3DDirections(Queue<BlockPos> storage, BlockPos checkPos) {
		storage.add(checkPos.add(Vectors.UP));
		storage.add(checkPos.add(Vectors.DOWN));
		storage.add(checkPos.add(Vectors.EAST));
		storage.add(checkPos.add(Vectors.WEST));
		storage.add(checkPos.add(Vectors.NORTH));
		storage.add(checkPos.add(Vectors.SOUTH));
		
		storage.add(checkPos.add(Vectors.NORTH_EAST));
		storage.add(checkPos.add(Vectors.NORTH_WEST));
		storage.add(checkPos.add(Vectors.SOUTH_EAST));
		storage.add(checkPos.add(Vectors.SOUTH_WEST));
		
		storage.add(checkPos.add(Vectors.UP_EAST));
		storage.add(checkPos.add(Vectors.UP_WEST));
		storage.add(checkPos.add(Vectors.UP_NORTH));
		storage.add(checkPos.add(Vectors.UP_SOUTH));
		
		storage.add(checkPos.add(Vectors.DOWN_EAST));
		storage.add(checkPos.add(Vectors.DOWN_WEST));
		storage.add(checkPos.add(Vectors.DOWN_NORTH));
		storage.add(checkPos.add(Vectors.DOWN_SOUTH));
		
		storage.add(checkPos.add(Vectors.UP_NORTH_EAST));
		storage.add(checkPos.add(Vectors.UP_NORTH_WEST));
		storage.add(checkPos.add(Vectors.UP_SOUTH_EAST));
		storage.add(checkPos.add(Vectors.UP_SOUTH_WEST));
		
		storage.add(checkPos.add(Vectors.DOWN_NORTH_EAST));
		storage.add(checkPos.add(Vectors.DOWN_NORTH_WEST));
		storage.add(checkPos.add(Vectors.DOWN_SOUTH_EAST));
		storage.add(checkPos.add(Vectors.DOWN_SOUTH_WEST));
	}
	
	public static void Enqueue2DDirections(Queue<BlockPos> storage, BlockPos checkPos) {
		storage.add(checkPos.add(Vectors.EAST));
		storage.add(checkPos.add(Vectors.WEST));
		storage.add(checkPos.add(Vectors.NORTH));
		storage.add(checkPos.add(Vectors.SOUTH));
		
		storage.add(checkPos.add(Vectors.NORTH_EAST));
		storage.add(checkPos.add(Vectors.NORTH_WEST));
		storage.add(checkPos.add(Vectors.SOUTH_EAST));
		storage.add(checkPos.add(Vectors.SOUTH_WEST));
	}
}