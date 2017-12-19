/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.workbench.common.stunner.cm.client.shape.view;

import com.ait.lienzo.client.core.shape.MultiPath;
import com.ait.lienzo.client.core.shape.wires.WiresShape;
import com.ait.lienzo.shared.core.types.ColorName;
import org.kie.workbench.common.stunner.cm.client.wires.AbstractCaseManagementShape;
import org.kie.workbench.common.stunner.lienzo.util.LienzoPaths;

/**
 * The Lienzo view implementation for the Activity shape.
 */
public class ActivityView extends AbstractCaseManagementShape<ActivityView> {

    public ActivityView(final double width,
                        final double height) {
        super(CM_SHAPE_VIEW_EVENT_TYPES,
              create(new MultiPath(),
                     width,
                     height),
              width,
              height);
        setResizable(false);
        setDraggable(true);
    }

    /**
     * Append the path parts for a Activity.
     * @param path The source multipath
     * @param w The activity width
     * @param h The activity height
     */
    private static MultiPath create(final MultiPath path,
                                    final double w,
                                    final double h) {
        LienzoPaths.rectangle(path,
                              w,
                              h,
                              0.0);
        return path
                .setFillColor(ColorName.LIGHTGOLDENRODYELLOW)
                .setStrokeColor(ColorName.BLACK);
    }

    @Override
    public ActivityView setSize(final double width,
                                final double height) {
        create(getPath().clear(),
               width,
               height);
        updateFillGradient(width,
                           height);
        refresh();
        return this;
    }

    @Override
    protected AbstractCaseManagementShape createGhost() {
        final ActivityView ghost = new ActivityView(getWidth(),
                                                    getHeight());
        for (WiresShape ws : getChildShapes()) {
            final AbstractCaseManagementShape wsg = ((AbstractCaseManagementShape) ws).getGhost();
            ghost.add(wsg);
        }

        return ghost;
    }
}
