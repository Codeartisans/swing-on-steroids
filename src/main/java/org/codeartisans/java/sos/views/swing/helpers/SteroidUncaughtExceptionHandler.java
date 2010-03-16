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

import javax.swing.SwingUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.codeartisans.java.sos.views.swing.components.ExceptionDialog;

public final class SteroidUncaughtExceptionHandler
        implements Thread.UncaughtExceptionHandler
{

    private static final Logger LOGGER = LoggerFactory.getLogger(SteroidUncaughtExceptionHandler.class);

    public void uncaughtException(final Thread t, final Throwable ex)
    {
        if (SwingUtilities.isEventDispatchThread()) {
            showException(ex);
        } else {
            SwingUtilities.invokeLater(new Runnable()
            {

                public void run()
                {
                    showException(ex);
                }
            });
        }


    }

    private void showException(Throwable ex)
    {
        LOGGER.error("UncaughtException handled: " + ex.getMessage(), ex);
        ExceptionDialog.showException(ex);
    }
}