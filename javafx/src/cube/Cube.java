package cube;

public class Cube {
	private int[][] data;
	public int[] chooseFrom = {1,2,3,4,5,6,7,8,9};
	private int upperLimit = 1000000;
	private int holeCount = 22;
	public int getWidth() {
		return chooseFrom.length;
	}
	
	public int[][] getData() {
		return data;
	}

	public void setData(int[][] data) {
		this.data = data;
	}

	public static void main(String[] args) throws Exception {
		Cube cube = new Cube();
		System.out.println(cube.getAverageTryCount());
	}
	public String getAverageTryCount() {
		int found = 0;
		for (int i = 1; i <= upperLimit; i++) {
			try {
				this.generateRandomData();
				found++;
			}
			catch (Exception e) {
			}
		}
		return upperLimit / found + "";
	}
	private void digHoles() throws Exception {
		int[] indexPool = new int[chooseFrom.length * chooseFrom.length];
		if (indexPool.length <= holeCount) throw new Exception("too many holes");
		for (int i = 0; i < indexPool.length; i++) {
			indexPool[i] = i;
		}
		int upperBound = indexPool.length;
		for (int i = 0; i < holeCount; i++) {
			int chosen = (int)(Math.random() * upperBound);
			int temp = indexPool[upperBound - 1];
			indexPool[upperBound - 1] = indexPool[chosen];
			indexPool[chosen] = temp;
			upperBound--;
		}
		for (int i = upperBound; i < indexPool.length; i++) {
			int y = indexPool[i] / chooseFrom.length;
			int x = indexPool[i] % chooseFrom.length;
			data[y][x] = -data[y][x];
		}
	}
	
	public boolean check() {
		//rows
		for (int i = 0; i < chooseFrom.length; i++) {
			int[] flags = new int[chooseFrom.length];
			for (int j = 0; j < chooseFrom.length; j++) {
				if (data[i][j] < 0) return false;
				int index = data[i][j] - 1;
				if (flags[index] == -1) return false;
				flags[index] = -1;
			}
		}
		//columns
		for (int i = 0; i < chooseFrom.length; i++) {
			int[] flags = new int[chooseFrom.length];
			for (int j = 0; j < chooseFrom.length; j++) {
				int index = data[j][i] - 1;
				if (flags[index] == -1) return false;
				flags[index] = -1;
			}
		}
		//nine cube
		for (int i = 0; i < 9; i += 3) {
			for (int j = 0; j < 9; j += 3) {
				int[] flags = new int[9];
				for (int y = i; y < i + 3; y++) {
					for (int x = j; x < j + 3; x++) {
						int index = data[y][x] - 1;
						if (flags[index] == -1) return false;
						flags[index] = -1;
					}
				}
			}
		}
		return true;
	}
	private int getRandom(int x, int y) {
		int[] indexPool = new int[chooseFrom.length];
		for (int i = 0; i < indexPool.length; i++) {
			indexPool[i] = i;
		}
		//row
		for (int i = 0; i < x; i++) {
			indexPool[data[y][i] - 1] = -1;
		}
		//column
		for (int i = 0; i < y; i++) {
			indexPool[data[i][x] - 1] = -1;
		}
		//9 cube
		int nineCubeX = x - x % 3;
		int nineCubeY = y - y % 3;
		for (int i = nineCubeY; i < y; i++) {
			for (int j = nineCubeX; j < nineCubeX + 3; j++) {
				indexPool[data[i][j] - 1] = -1;
			}
		}
		for (int j = nineCubeX; j < x; j++) {
			indexPool[data[y][j] - 1] = -1;
		}
		int poolSize = indexPool.length;
		for (int i = 0; i < poolSize; i++) {
			if (indexPool[i] == -1) {
				int temp = indexPool[poolSize - 1];
				indexPool[i] = temp;
				if (temp == -1) {
					i--;
				}
				poolSize--;
			}
		}
		return chooseFrom[indexPool[(int)(Math.random() * poolSize)]];
	}
	public String toString() {
		String s = "";
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				int value = data[i][j];
				s += ((value > 0 ? value : " ") + "\t");
			}
			s += "\n";
		}
		return s;
	}
	public String getAnswer() {
		String s = "";
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				int value = data[i][j];
				s += value + "\t";
			}
			s += "\n";
		}
		return s;
	}
	public void generate() throws Exception {
		for (int i = 0; i < upperLimit; i++) {
			try {
				this.generateRandomData();
				System.out.println(this.check());
				this.digHoles();
				return;
			}
			catch (Exception e) {
			}
		}
		throw new Exception("cannot generate legal data after trying " + this.upperLimit + " times");
	}
	private void generateRandomData() {
		data = new int[chooseFrom.length][chooseFrom.length];
		for (int y = 0; y < data.length; y++) {
			for (int x = 0; x < data[y].length; x++) {
				data[y][x] = getRandom(x, y);
			}
		}
	}

	public int getHoleCount() {
		return holeCount;
	}

	public void setHoleCount(int holeCount) {
		this.holeCount = holeCount;
	}
}
