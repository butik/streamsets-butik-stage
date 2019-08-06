/**
 * Copyright 2015 StreamSets Inc.
 *
 * Licensed under the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.butik.streamsets.origin.stage;

import com.streamsets.pipeline.lib.event.NoMoreDataEvent;
import com.streamsets.pipeline.api.*;
import ru.butik.streamsets.origin.lib.ButikOrigin;
import ru.butik.streamsets.origin.lib.SourceOrigin;
import ru.butik.streamsets.origin.lib.SourceOriginChooserValues;

@StageDef(
    version = 1,
    label = "Butik.ru grpc origin",
    description = "Stream entities from butik.ru grpc server to streamsets pipeline",
    icon = "default.png",
    execution = ExecutionMode.STANDALONE,
    recordsByRef = true,
    onlineHelpRefUrl = "",
    producesEvents = true,
    eventDefs = {NoMoreDataEvent.class}
)
@ConfigGroups(value = Groups.class)
@GenerateResourceBundle
public class OriginDSource extends OriginSource {

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
      defaultValue = "",
      label = "GRPC Server url",
      displayPosition = 10,
      group = "ORIGIN"
  )
  public String server;

  /** {@inheritDoc} */
  @Override
  public String getServer() {
    return server;
  }

  @ConfigDef(
          required = true,
          type = ConfigDef.Type.MODEL,
          defaultValue = "NONE",
          label = "Butik.ru source",
          displayPosition = 20,
          group = "ORIGIN"
  )
  @ValueChooserModel(SourceOriginChooserValues.class)
  public SourceOrigin origin = SourceOrigin.NONE;

  @Override
  public Class<? extends ButikOrigin> getOrigin() {
    return origin.getValue();
  }
}
