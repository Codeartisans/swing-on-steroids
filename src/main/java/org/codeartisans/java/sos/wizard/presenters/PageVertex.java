/*
 * Copyright (c) 2010 Paul Merlin <paul@nosphere.org>
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
package org.codeartisans.java.sos.wizard.presenters;

import org.codeartisans.java.sos.wizard.model.WizardModel;
import org.codeartisans.java.sos.wizard.model.WizardPageID;
import org.codeartisans.java.sos.wizard.views.WizardPageView;

/**
 * @author Paul Merlin 
 */
public class PageVertex<M extends WizardModel>
{

    private final WizardPagePresenter<M, ? extends WizardPageView> wizardPagePresenter;

    public PageVertex( WizardPagePresenter<M, ? extends WizardPageView> wizardPagePresenter )
    {
        this.wizardPagePresenter = wizardPagePresenter;
    }

    public WizardPagePresenter<M, ? extends WizardPageView> presenter()
    {
        return wizardPagePresenter;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder( "PageVertex{" );
        sb.append( wizardPagePresenter.wizardPageID() );
        return sb.append( "}" ).toString();
    }

    public WizardPageID wizardPageID()
    {
        return wizardPagePresenter.wizardPageID();
    }

    @Override
    @SuppressWarnings( "AccessingNonPublicFieldOfAnotherObject" )
    public boolean equals( Object obj )
    {
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        @SuppressWarnings( "unchecked" )
        final PageVertex<M> other = ( PageVertex<M> ) obj;
        if ( this.wizardPagePresenter != other.wizardPagePresenter && ( this.wizardPagePresenter == null || !this.wizardPagePresenter.equals( other.wizardPagePresenter ) ) ) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 59 * hash + ( this.wizardPagePresenter != null ? this.wizardPagePresenter.hashCode() : 0 );
        return hash;
    }

}
