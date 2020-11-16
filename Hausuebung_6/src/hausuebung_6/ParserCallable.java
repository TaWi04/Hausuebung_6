/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hausuebung_6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tamara
 */
public class ParserCallable implements Callable<PrioritisedOutput> {

    private int priority;
    private Tag tag;

    public ParserCallable(int priority, Tag tag) {
        this.priority = priority;
        this.tag = tag;
    }

    @Override
    public PrioritisedOutput call() throws Exception {

        ExecutorService ex = Executors.newCachedThreadPool();

        List<ParserCallable> parser = new ArrayList<>();
        List<PrioritisedOutput> outputs = Parser.splitLine(tag.getContent());

        for (int i = 0; i < outputs.size(); i++) {
            if (outputs.get(i).getResult().startsWith("<")) {
                parser.add(new ParserCallable(outputs.get(i).getPrioity(), new Tag(outputs.get(i).getResult())));
            }
        }

        List<Future<PrioritisedOutput>> results = ex.invokeAll(parser);
        results.stream()
                .map(r -> {
                    try {
                        return r.get();
                    } catch (InterruptedException ex1) {
                        Logger.getLogger(ParserCallable.class.getName()).log(Level.SEVERE, null, ex1);
                    } catch (ExecutionException ex1) {
                        Logger.getLogger(ParserCallable.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                    return null;
                })
                .forEach(r -> {
                    outputs.set(outputs.indexOf(r), r);
                });

        ex.shutdown();

        String temp = "";
        for (PrioritisedOutput output : outputs) {
            temp += output.getResult();
        }

        return new PrioritisedOutput(priority, temp);
    }

}
