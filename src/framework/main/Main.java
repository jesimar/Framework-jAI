package framework.main;

import framework.method.eda.UMDA;
import framework.method.eda.UMDAc;
import framework.method.eda.cGA;
import framework.method.ga.AGsr;
import framework.method.ga.DE_b;
import framework.method.ga.DE_c;
import framework.method.ga.sGA;
import framework.method.get.GerarETestar;
import framework.method.grasp.Grasp;
import framework.problem.cannibals.SolveCannibals;
import framework.problem.kmeans.Kmeans;
import framework.problem.map_coloring.SolveMap;
import framework.problem.nqueens.SolveNQueen;

/**
 *
 * @author jesimar
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        SolveNQueen solveQueen = new SolveNQueen();
        
//        SolveCannibals solveCannibals = new SolveCannibals();
        
//        SolveMap solveMap = new SolveMap();
        
//        Kmeans kmeans = new Kmeans(100, 2);
//        kmeans.initData();
//        kmeans.exec();
//        kmeans.printData();
        
//        GerarETestar gerar = new GerarETestar();
//        gerar.exec();
        
//        Grasp grasp = new Grasp(); 
//        grasp.exec();
        
//        cGA cga = new cGA();
//        cga.exec();      
        
//        UMDA umda = new UMDA();
//        umda.exec();
        
//        UMDAc umdac = new UMDAc();
//        umdac.exec();
        
//        UMDAcBimodal umdacBimodal = new UMDAcBimodal();
//        umdacBimodal.exec();
        
        //Não esta muito bom
//        MIMICcG mimic = new MIMICcG();
//        mimic.exec();
        
//        AGsr agsr = new AGsr();
//        agsr.exec();
        
//        sGA sGA = new sGA();
//        sGA.exec();
        
        //Não esta muito bom
//        AGsrMultiCros agsrMC = new AGsrMultiCros();
//        agsrMC.exec();
                
        //Não terminado
//        EcGA ecga = new EcGA();
//        ecga.exec();
                
//        DE_b de_b = new DE_b();
//        de_b.exec();
        
//        DE_c de_c = new DE_c();
//        de_c.exec();
        
        //Não terminado
//        PSO pso = new PSO();
//        pso.exec();
        
    }
}
