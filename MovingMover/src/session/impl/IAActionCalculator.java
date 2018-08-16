package session.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import session.abstr.Action;
import session.abstr.IA;

public class IAActionCalculator {    
     
    private ExecutorService executor 
      = Executors.newSingleThreadExecutor();
     
    public Future<Action> calculate(IA iA,Player player, Game game) {        
        return executor.submit(() -> {
            return iA.calculNextAction(player, game);
        });
    }
}
