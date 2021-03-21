package model;

import java.util.ArrayList;
import java.util.Collections;

public class Test {
	public static void main(String args[]) {
		
		
		Grid5T g = new Grid5T();
		//g.addTrace(5,12);
		g.addPoint(8,10);
		System.out.println("Je");
		//g.searchH(6,9);
		g.addPoint(7, 10);
		g.addPoint(7, 10);
		
	//	g.affiche();
		
	//	for(Ligne l : g.getLigneCreated()) System.out.println(l);
		
		
		
		//mettre ligne puis colonnne
		//System.out.println(g.searchV(5,12));
		//System.out.println(g.)
		//g.affiche();
		
	/*	 ArrayList<Point> l = new ArrayList<Point>();
		 l.add(new Point(1,2,false));
		 l.add(new Point(1,4,false));
		 l.add(new Point(1,1,false));
		 l.add(new Point(1,3,false));
		 l.add(new Point(1,5,false));
		// l.add(new Point(1,6,false));
		 //l.add(new Point(1,7,false));
		 //l.add(new Point(1,8,false));
		 
		 Collections.sort(l);
		 Ligne line = new Ligne(l);
		 for(Point e:l)
			 System.out.println(e.toString());
		 
		 Ligne[] li = Ligne.split(l);
		 for(int i=0;i<li.length;i++)
			 System.out.println(li[i].toString());
		 g.linkedPoint(line);
		 
		 System.out.println(line.toString());
		 
		 for(Point p : line.getAllPointLine())
			 System.out.println(p.toString());
		 
		*/
		
		boolean[][] grilleInit = {
				{false,false,false,false,false,false,false,false,false,false,false,false,false,false},
				{false,false,false,false,false,false,false,false,false,false,false,false,false,false},
				{false,false,false,false,false,true,true,true,true,false,false,false,false,false},
				{false,false,false,false,false,true,false,false,true,false,false,false,false,false},
				{false,false,false,false,false,true,false,false,true,false,false,false,false,false},
				{false,false,true,true,true,true,false,false,true,true,true,true,false,true},
				{false,false,true,false,false,false,false,false,false,false,false,true,false,false},
				{false,false,true,false,false,false,false,false,false,false,false,true,false,false},
				{false,false,true,true,true,true,false,false,true,true,true,true,false,false},
				{false,false,false,false,false,true,false,false,true,false,false,false,false,false},
				{false,false,false,false,false,true,false,false,true,false,false,false,false,false},
				{false,false,false,false,false,true,true,true,true,false,false,false,false,false},
				{false,false,false,false,false,false,false,false,false,false,false,false,false,false},
		};
	}
}