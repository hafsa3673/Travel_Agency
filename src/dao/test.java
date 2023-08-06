package dao;

import java.util.List;

import entities.Voyage;
import entities.Voyage_acc;

public class test {
public static void main(String [] args) {
IVoyage_acc v = new VoyageImp_acc(DBconnect.getConnection());
List <Voyage_acc> list = v.getVoyageAccParAct("Balade");
System.out.println(list);
System.out.println("sdfghj");
}

}
