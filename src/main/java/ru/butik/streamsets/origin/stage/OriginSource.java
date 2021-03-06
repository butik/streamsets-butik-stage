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

import _ss_com.streamsets.pipeline.lib.event.NoMoreDataEvent;
import ru.butik.streamsets.origin.lib.ButikOrigin;
import ru.butik.streamsets.origin.lib.Errors;
import com.streamsets.pipeline.api.BatchMaker;
import com.streamsets.pipeline.api.Field;
import com.streamsets.pipeline.api.Record;
import com.streamsets.pipeline.api.StageException;
import com.streamsets.pipeline.api.base.BaseSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This source is an example and does not actually read from anywhere.
 * It does however, generate generate a simple record with one field.
 */
public abstract class OriginSource extends BaseSource {

  /**
   * Gives access to the UI configuration of the stage provided by the {@link OriginDSource} class.
   */
  public abstract String getServer();
  public abstract Class<? extends ButikOrigin> getOrigin();

  @Override
  protected List<ConfigIssue> init() {
    // Validate configuration values and open any required resources.
    List<ConfigIssue> issues = super.init();

    // If issues is not empty, the UI will inform the user of each configuration issue in the list.
    return issues;
  }

  /** {@inheritDoc} */
  @Override
  public void destroy() {
    // Clean up any open resources.
    super.destroy();
  }

  /** {@inheritDoc} */
  @Override
  public String produce(String lastSourceOffset, int maxBatchSize, BatchMaker batchMaker) throws StageException {
    // Offsets can vary depending on the data source. Here we use an integer as an example only.
    long nextSourceOffset = 0;
    if (lastSourceOffset != null) {
      nextSourceOffset = Long.parseLong(lastSourceOffset);
    }

    NoMoreDataEvent.EVENT_CREATOR.create(getContext(), getContext())
            .with(NoMoreDataEvent.RECORD_COUNT, 0)
            .with(NoMoreDataEvent.ERROR_COUNT, 0)
            .with(NoMoreDataEvent.FILE_COUNT, 0)
            .createAndSend();

    return String.valueOf(nextSourceOffset);
  }

}
