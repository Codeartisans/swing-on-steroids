/*
 * Copyright (c) 2009, Paul Merlin. All Rights Reserved.
 * Copyright (c) 2010, Fabien Barbero. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.swing.on.steroids.messagebus;

import com.google.inject.Inject;
import org.swing.on.steroids.threading.WorkQueue;

/**
 * MessageBus Implementation that use a thread to deliver Messages to Subscribers sequentially.
 * The publish method returns immediatly.
 */
public final class SingleThreadDeliveryMessageBus
        extends BaseMessageBus
{

    private final WorkQueue workQueue;

    @Inject
    public SingleThreadDeliveryMessageBus( WorkQueue workQueue )
    {
        this.workQueue = workQueue;
    }

    @Override
    public <S extends Subscriber> void publish( final Message<S> message )
    {
        workQueue.enqueue( new Runnable()
        {

            @Override
            public void run()
            {
                if ( !vetoed( message ) ) {
                    for ( S eachSubscriber : subscribers( message.getMessageType() ) ) {
                        try {
                            message.deliver( eachSubscriber );
                        } catch ( DeliveryRefusalException refusal ) {
                        }
                    }
                }
            }

        } );
    }

}
