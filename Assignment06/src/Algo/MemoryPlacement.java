package Algo;

import java.util.Scanner;
import java.util.Arrays;

public class MemoryPlacement{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of memory blocks: ");
        int numBlocks = sc.nextInt();
        int[] blockSizes = new int[numBlocks];
        for (int i = 0; i < numBlocks; i++) {
            System.out.print("Enter size of block " + (i + 1) + " : ");
            blockSizes[i] = sc.nextInt();
        }

        System.out.print("\nEnter the number of processes: ");
        int numProcesses = sc.nextInt();
        int[] processSizes = new int[numProcesses];
        for (int i = 0; i < numProcesses; i++) {
            System.out.print("Enter size of process " + (i + 1) + " : ");
            processSizes[i] = sc.nextInt();
        }

        
        System.out.println("\n(e.g., Block 100, 500, 200, 300, 600. Process 212, 417, 112, 426)");

        firstFit(blockSizes.clone(), processSizes);
        bestFit(blockSizes.clone(), processSizes);
        worstFit(blockSizes.clone(), processSizes);
        nextFit(blockSizes.clone(), processSizes);
        
        sc.close();
    }

    /**
     * 1. First Fit Strategy
     * Allocates the first block that is large enough 
     **/ 
    public static void firstFit(int[] blocks, int[] processes) {
        int[] allocation = new int[processes.length];
        Arrays.fill(allocation, -1); // -1 means not allocated

        for (int i = 0; i < processes.length; i++) { // For each process
            for (int j = 0; j < blocks.length; j++) { // Find first block
                if (blocks[j] >= processes[i]) {
                    allocation[i] = j; // Allocate block j
                    blocks[j] -= processes[i]; // Reduce block size
                    break; // Go to the next process
                }
            }
        }
        printResults("First Fit", processes, allocation);
    }

    /**
     * 2. Best Fit Strategy 
     * Allocates the smallest block that is large enough.
     */
    public static void bestFit(int[] blocks, int[] processes) {
        int[] allocation = new int[processes.length];
        Arrays.fill(allocation, -1);

        for (int i = 0; i < processes.length; i++) {
            int bestIdx = -1; // Index of the best fitting block
            
            for (int j = 0; j < blocks.length; j++) {
                if (blocks[j] >= processes[i]) {
                    if (bestIdx == -1) {
                        bestIdx = j; // This is the first fit we've found
                    } else if (blocks[j] < blocks[bestIdx]) {
                        bestIdx = j; // This one is smaller (better)
                    }
                }
            }

            if (bestIdx != -1) {
                allocation[i] = bestIdx;
                blocks[bestIdx] -= processes[i];
            }
        }
        printResults("Best Fit", processes, allocation);
    }

    /**
     * 3. Worst Fit Strategy 
     * Allocates the largest block that is large enough.
     */
    public static void worstFit(int[] blocks, int[] processes) {
        int[] allocation = new int[processes.length];
        Arrays.fill(allocation, -1);

        for (int i = 0; i < processes.length; i++) {
            int worstIdx = -1; // Index of the worst (largest) block
            
            for (int j = 0; j < blocks.length; j++) {
                if (blocks[j] >= processes[i]) {
                    if (worstIdx == -1) {
                        worstIdx = j; // This is the first fit
                    } else if (blocks[j] > blocks[worstIdx]) {
                        worstIdx = j; // This one is larger (worse)
                    }
                }
            }

            if (worstIdx != -1) {
                allocation[i] = worstIdx;
                blocks[worstIdx] -= processes[i];
            }
        }
        printResults("Worst Fit", processes, allocation);
    }

    /**
     * 4. Next Fit Strategy 
     * Allocates the first block that fits, starting from the last allocation point.
     */
    public static void nextFit(int[] blocks, int[] processes) {
        int[] allocation = new int[processes.length];
        Arrays.fill(allocation, -1);
        
        int lastAllocationIdx = 0; // Start searching from block 0

        for (int i = 0; i < processes.length; i++) {
            // Search from the last allocation point, wrapping around
            // We scan at most 'blocks.length' times
            for (int k = 0; k < blocks.length; k++) {
                int j = (lastAllocationIdx + k) % blocks.length; // Wraps around

                if (blocks[j] >= processes[i]) {
                    allocation[i] = j;
                    blocks[j] -= processes[i];
                    lastAllocationIdx = j; // Update the starting point for next search
                    break; // Go to the next process
                }
            }
            // If we loop all the way around and find no block, 
            // the process remains unallocated (allocation[i] is still -1)
        }
        printResults("Next Fit", processes, allocation);
    }

    /**
     * Helper method to print the results in a table format.
     */
    public static void printResults(String strategyName, int[] processSizes, int[] allocation) {
        System.out.println("\n--- " + strategyName + " Strategy ---");
        // Print the table header
        System.out.println("Process No\tProcess Size\tBlock No.");
        System.out.println("-------------------------------------------------");

        for (int i = 0; i < processSizes.length; i++) {
            // Check if the process was allocated
            String blockNo;
            if (allocation[i] != -1) {
                blockNo = String.valueOf(allocation[i] + 1); // +1 for 1-based index
            } else {
                blockNo = "Not Allocated";
            }
            
            // Print the row using simple tabs (\t) to separate columns
            System.out.println("P" + (i + 1) + "\t\t" + processSizes[i] + "\t\t" + blockNo);
        }
    }
}