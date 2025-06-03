package 이용태;

import java.util.Arrays;

public class A0010_이차원배열연습문제2 {
	private static class Position {
		int x;
		int y;

		Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private enum Direction {
		LEFT, DOWN, RIGHT, UP
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] arr = new int[5][5];
		Position pos = new Position(0, 0);
		Position size = new Position(5, 5);
		Direction cmd = Direction.RIGHT;

		for (int loop = 1; loop <= arr.length * arr[0].length; loop++) {
			arr[pos.y][pos.x] = loop;
			displace(pos, cmd);
			if(pos.x >= size.x || pos.x < 0 || pos.y >= size.y || pos.y < 0 || arr[pos.y][pos.x] != 0) {
				cmd = turn(cmd);
				cmd = turn(cmd);
				displace(pos, cmd);
				cmd = turn(cmd);
				cmd = turn(cmd);
				cmd = turn(cmd);
				displace(pos, cmd);
			}
		}
		for (int[] is : arr) {
			System.out.println(Arrays.toString(is));
		}
	}

	private static Direction turn(Direction cmd) {
		// TODO Auto-generated method stub
		switch (cmd) {
		case RIGHT: {
			return Direction.DOWN;
		}
		case DOWN: {
			return Direction.LEFT;
		}
		case LEFT: {
			return Direction.UP;
		}
		case UP: {
			return Direction.RIGHT;
		}
		}
		return null;
	}

	private static void displace(Position pos, Direction cmd) {
		switch (cmd) {
		case RIGHT: {
			pos.x++;
			break;
		}
		case DOWN: {
			pos.y++;
			break;
		}
		case LEFT: {
			pos.x--;
			break;
		}
		case UP: {
			pos.y--;
			break;
		}
		}
	}

}
