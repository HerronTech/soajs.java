/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soajs.filters;

import java.util.TimerTask;

/**
 *
 * @author Etienne
 */
public class SoajsTimerTask extends TimerTask {
    public void run() {
       System.out.println("Timer Task set - reloading registry ...");
       SoajsRegistry.execRegistry();
    }
}