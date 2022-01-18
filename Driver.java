import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//store each team
		ArrayList<String> teams = new ArrayList<String>();
		//x coordinate
		ArrayList<Double> xc = new ArrayList<Double>();
		//y coordinate
		ArrayList<Double> yc = new ArrayList<Double>();
		//if the field goal was made
		ArrayList<Integer> fgmade = new ArrayList<Integer>();

		
		String line = "";
		String splitBy = ",";
		//using comma as delimiter it reads in the file and then seperates it and then adds it to its respective arraylist
		try {
			BufferedReader br = new BufferedReader(new FileReader("shots_data.csv"));
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(splitBy);
				
				teams.add(parts[0]);
				
				double xctemp = Double.parseDouble(parts[1]);
				xc.add(xctemp);
				
				double yctemp = Double.parseDouble(parts[2]);
				yc.add(yctemp);
				
				int fgtemp = Integer.parseInt(parts[3]);
				fgmade.add(fgtemp);
			}
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//total shot attempts by team A
		int totalShotsA = 0;
		//2 pointers made by team A
		int twoPointersAmake = 0;
		//corner 3 made by team A
		int cThreeAmake = 0;
		//noncorner 3 made by team A
		int ncThreeAmake = 0;
		//2 pointers attempted by team A
		int twoPointersAtry = 0;
		//corner 3s attempted by team A
		int cThreeAtry = 0;
		//noncorner 3s attempted by team A
		int ncThreeAtry = 0;
		
		//total shot attempts by team B
		int totalShotsB = 0;
		//2 pointers made by team B
		int twoPointersBmake = 0;
		//corner 3 made by team B
		int cThreeBmake = 0;
		//noncorner 3 made by team B
		int ncThreeBmake = 0;
		//2 pointers attempted by team B
		int twoPointersBtry = 0;
		//corner 3s attempted by team B
		int cThreeBtry = 0;
		//noncorner 3s attempted by team B
		int ncThreeBtry = 0;
		
		//runs through each shot and depending on its distance, incrememnts the values above
		for(int i = 0; i < teams.size(); i++) {
			//shows which team it is
			if(teams.get(i).equals("Team A")) {
				//checks to see if it is possibly a corner 3 or not based off yc
				if(yc.get(i) <= 7.8) {
					//calculates distance from basket
					double dist = Math.sqrt((yc.get(i) * yc.get(i)) + (xc.get(i) * xc.get(i))); 
					//compares distance to distance of 3pt line
					if(dist > 22.0) {
						if(fgmade.get(i) == 1) {
							cThreeAmake++;
							cThreeAtry++;
						}
						else {
							cThreeAtry++;
						}
					}
					else {
						if (fgmade.get(i) == 1) {
							twoPointersAmake++;
							twoPointersAtry++;
						}
						else {
							twoPointersAtry++;
						}
					}
				}
				else {
					double dist = Math.sqrt((yc.get(i) * yc.get(i)) + (xc.get(i) * xc.get(i)));
					if(dist > 23.75) {
						if(fgmade.get(i) == 1) {
							ncThreeAmake++;
							ncThreeAtry++;
						}
						else {
							ncThreeAtry++;
						}
					}
					else {
						if(fgmade.get(i) == 1) {
							twoPointersAmake++;
							twoPointersAtry++;
						}
						else {
							twoPointersAtry++;
						}
					}
				}
				totalShotsA++;
			}
			//team b, does the same thing as above
			else {
				if(yc.get(i) <= 7.8) {
					double dist = Math.sqrt((yc.get(i) * yc.get(i)) + (xc.get(i) * xc.get(i))); 
					if(dist > 22.0) {
						if(fgmade.get(i) == 1) {
							cThreeBmake++;
							cThreeBtry++;
						}
						else {
							cThreeBtry++;
						}
					}
					else {
						if (fgmade.get(i) == 1) {
							twoPointersBmake++;
							twoPointersBtry++;
						}
						else {
							twoPointersBtry++;
						}
					}
				}
				else {
					double dist = Math.sqrt((yc.get(i) * yc.get(i)) + (xc.get(i) * xc.get(i)));
					if(dist > 23.75) {
						if(fgmade.get(i) == 1) {
							ncThreeBmake++;
							ncThreeBtry++;
						}
						else {
							ncThreeBtry++;
						}
					}
					else {
						if(fgmade.get(i) == 1) {
							twoPointersBmake++;
							twoPointersBtry++;
						}
						else {
							twoPointersBtry++;
						}
					}
				}
				totalShotsB++;
			}
		}
		//total shots made by team A
		int totalShotsAmake = twoPointersAmake + cThreeAmake + ncThreeAmake;
		//total 3 pointers made by team A
		int t3pmA = cThreeAmake + ncThreeAmake;
		
		//total shots made by team B
		int totalShotsBmake = twoPointersBmake + cThreeBmake + ncThreeBmake;
		//total 3 pointers made by team B
		int t3pmB = cThreeBmake + ncThreeBmake;
		//calculating shot distribution per team in each zone
		double twoPointSDA = (double) twoPointersAtry / (double) totalShotsA;
		System.out.println("Team A 2 point attempts: " + twoPointSDA);
		double ncThreeSDA = (double)ncThreeAtry / (double)totalShotsA ;
		System.out.println("Team A NC3 SDA: " + ncThreeSDA);
		double cThreeSDA = (double)cThreeAtry / (double)totalShotsA ;
		System.out.println("Team A C3 SDA: " + cThreeSDA);
		
		double twoPointSDB = (double)twoPointersBtry / (double)totalShotsB ;
		System.out.println("Team B 2P SD: " + twoPointSDB);
		double ncThreeSDB = (double)ncThreeBtry / (double)totalShotsB;
		System.out.println("Team B NC3 SD" + ncThreeSDB);
		double cThreeSDB = (double)cThreeBtry / (double)totalShotsB;
		System.out.println("Team B C3 SD: " + cThreeSDB);
		
		//effective field goal percentage
		double a2PEFG = ((double)twoPointersAmake + (0.5 * (double)t3pmA)) / (double)totalShotsA;
		System.out.println("A 2P EFG: " + a2PEFG);
		double b2PEFG = ((double)twoPointersBmake + (0.5 * (double)t3pmB)) / (double)totalShotsB;
		System.out.println("B 2P EFG: " + b2PEFG);
		double aNCEFG = ((double)ncThreeAmake + (0.5 * (double)t3pmA)) / (double)totalShotsA;
		System.out.println("A NC3 EFG: " + aNCEFG);
		double bNCEFG = ((double)ncThreeBmake + (0.5 * (double)t3pmB)) / (double)totalShotsB;
		System.out.println("B NC3 EFG: " + bNCEFG);
		double aC3EFG = ((double)cThreeAmake + (0.5 * (double)t3pmA)) / (double)totalShotsA;
		System.out.println("A C3 EFG: " + aC3EFG);
		double bC3EFG = ((double)cThreeBmake + (0.5 * (double)t3pmB)) / (double)totalShotsB;
		System.out.println("B C3 EFG: " + bC3EFG);
		double aEFG = ((double)totalShotsAmake + (0.5 * (double)t3pmA)) / (double)totalShotsA;
		double bEFG = ((double)totalShotsBmake + (0.5 * (double)t3pmB)) / (double)totalShotsB;
		
		System.out.println("Team A EFG: " + aEFG);
		System.out.println("Team B EFG: " + bEFG);

	}
	

}
