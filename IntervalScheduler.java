package basics;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.IllegalFormatException;

class Job implements Comparable {
	int start, end;
	int pos;

	Job(int start, int end, int pos) {
		this.start = start;
		this.end = end;
		this.pos = pos;
	}

	@Override
	public int compareTo(Object job) {
		if (this.end <= ((Job) job).end) {
			return -1;
		} else if (this.end == ((Job) job).end) {
			return 0;
		} else {
			return 1;
		}
	}

}

public class IntervalScheduler {

	public static void main(String[] args) throws IOException {
		BufferedReader buffer = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/src/input.txt"));
		String line = "";
		Job[] totaljobs = null;
		int i = 0;

		try {
			while ((line = buffer.readLine()) != null) {
				String[] values = line.split(",");

				if (values.length == 1) {
					totaljobs = new Job[Integer.valueOf(values[0])];
				} else if (values.length == 2) {
					if (i < totaljobs.length) {
						int start = Integer.valueOf(values[0]);
						int end = Integer.valueOf(values[1]);
						if (start > end) {
							throw new IllegalArgumentException();
						}
						Job j = new Job(start, end, i + 1);
						totaljobs[i] = j;
						i++;
					} else {
						throw new IllegalArgumentException();
					}
				} else {
					throw new IllegalArgumentException();
				}
			}
			if (i != totaljobs.length) {
				throw new IllegalArgumentException();
			}
		} catch (NumberFormatException nfe) {
			System.out.println("Unexpected data found. Correct the input file.");
			return;
		} catch (IllegalArgumentException iae) {
			System.out.println("Invalid input. Please correct the input file and try again.");
			return;
		}

		System.out.println("\nThe given jobs are:");
		printArray(totaljobs);
		Arrays.sort(totaljobs);
		System.out.println(" ");
		scheduleNonOverlappingJobs(totaljobs);
	}

	private static void scheduleNonOverlappingJobs(Job[] totaljobs) {

		int i = 0;
		System.out.println(" ");
		System.out.println("The mutually compatible jobs are: ");
		System.out.print(totaljobs[i].pos);

		for (int j = i + 1; j < totaljobs.length; j++) {
			if (totaljobs[i].end <= totaljobs[j].start)

			{
				if (j + 1 <= totaljobs.length) {
					System.out.print(",");
				}
				System.out.print(totaljobs[j].pos);
				i = j;
			}
		}

	}

	public static void printArray(Job[] totaljobs) {
		for (int j = 0; j < totaljobs.length; j++) {
			System.out.println(totaljobs[j].pos + ") " + "[" + totaljobs[j].start + "," + totaljobs[j].end + "]");
		}
	}
}

