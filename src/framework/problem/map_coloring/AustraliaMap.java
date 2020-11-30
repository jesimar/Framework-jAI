/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.problem.map_coloring;

/**
 *
 * @author jesimar
 */
public class AustraliaMap {
    
    public static final int MAX_LEVEL = 7;
    public int level;
    public static int cost;
    
    //Variáveis: WA, NT, Q, NSW, V, SA, T           0     1     2    3      4    5     6
    private final String[] variable = new String[]{"WA", "NT", "Q", "NSW", "V", "SA", "T"};     
    
    //Domínio: D_i = {vermelho,verde,azul}
    private final String[] domain = new String[]{"vermelho", "verde", "azul"};
    
    //Restrições: regiões adjacentes devem possuir cores diferentes
    
    private final int AMOUNT_STATES = 7;
    private final State states[] = new State[AMOUNT_STATES];
    
    public AustraliaMap(){
        for (int i = 0; i < states.length; i++){
            states[i] = new State(variable[i]);
        }
        defineAdjacents();
    }
    
    public AustraliaMap(AustraliaMap australia){
        for (int i = 0; i < states.length; i++){
            states[i] = new State(australia.states[i].getName(), australia.states[i].getColor());
        }
        defineAdjacents();
    }
    
    private void defineAdjacents(){
        states[0].addAdjacent(states[1]);
        states[0].addAdjacent(states[5]);
        
        states[1].addAdjacent(states[0]);
        states[1].addAdjacent(states[2]);
        states[1].addAdjacent(states[5]);
        
        states[2].addAdjacent(states[1]);
        states[2].addAdjacent(states[3]);
        states[2].addAdjacent(states[5]);
        
        states[3].addAdjacent(states[2]);
        states[3].addAdjacent(states[4]);
        states[3].addAdjacent(states[5]);
        
        states[4].addAdjacent(states[3]);
        states[4].addAdjacent(states[5]);
        
        states[5].addAdjacent(states[0]);
        states[5].addAdjacent(states[1]);
        states[5].addAdjacent(states[2]);
        states[5].addAdjacent(states[3]);
        states[5].addAdjacent(states[4]); 
    }

    public State[] getStates() {
        return states;
    }

    public String[] getDomain() {
        return domain;
    }
    
    public void action(int index, String color){
        states[index].setColor(color);
    }
    
    public void printState(){
        for (State state : states){
            System.out.println("State: " + state.getName() + " --> " + state.getColor());
        }
    }
}
