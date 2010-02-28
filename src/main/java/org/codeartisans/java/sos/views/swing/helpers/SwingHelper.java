/*
 * Copyright (c) 2009 Paul Merlin <paul@nosphere.org>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.codeartisans.java.sos.views.swing.helpers;

import java.lang.reflect.InvocationTargetException;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.RepaintManager;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Collection of static methods dealing with Swing application init.
 */
public final class SwingHelper
{

    private SwingHelper()
    {
    }

    /**
     * Register enforcers to ensure the EDT stays clean.
     */
    public static void initSafeSwing()
    {
        RepaintManager.setCurrentManager( new CheckThreadViolationRepaintManager() );
        EventDispatchThreadHangMonitor.initMonitoring();
    }

    /**
     * Register SwingHelper UncaughtExceptionHandler and an AwtHandler for modal dialogs.
     */
    public static void initExceptionHandling()
    {
        Thread.setDefaultUncaughtExceptionHandler( new SteroidUncaughtExceptionHandler() );
        System.setProperty( "sun.awt.exception.handler", SteroidAwtHandler.class.getName() );
    }

    /**
     * Init given Look And Feel.
     * @param className LAF class name
     */
    public static void initLookAndFeel( final String className )
    {
        try {
            SwingUtilities.invokeAndWait( new Runnable()
            {

                @Override
                public void run()
                {
                    try {
                        JFrame.setDefaultLookAndFeelDecorated( true );
                        JDialog.setDefaultLookAndFeelDecorated( true );
                        UIManager.setLookAndFeel( className );
                    } catch ( Exception ex ) {
                        throw new RuntimeException( ex.getMessage(), ex );
                    }
                }
            } );
        } catch ( InterruptedException ex ) {
            throw new RuntimeException( ex.getMessage(), ex );
        } catch ( InvocationTargetException ex ) {
            throw new RuntimeException( ex.getMessage(), ex );
        }
    }
}
