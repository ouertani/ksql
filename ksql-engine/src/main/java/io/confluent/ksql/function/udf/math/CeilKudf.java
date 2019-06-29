/*
 * Copyright 2018 Confluent Inc.
 *
 * Licensed under the Confluent Community License (the "License"); you may not use
 * this file except in compliance with the License.  You may obtain a copy of the
 * License at
 *
 * http://www.confluent.io/confluent-community-license
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package io.confluent.ksql.function.udf.math;

import io.confluent.ksql.function.KsqlFunctionException;
import io.confluent.ksql.function.udf.Kudf;

public class CeilKudf implements Kudf {

  public static final String NAME = "CEIL";

  @Override
  public Object evaluate(final Object... args) {
    if (args.length != 1 && args.length != 2) {
      throw new KsqlFunctionException("Ceil udf should have one or two input arguments.");
    }

    final Double number = (Double) args[0];

    if (args.length == 1) {
      return (long) Math.ceil(number);
    }
    final Double round = Math.pow(10, (Integer) args[1]);
    return Math.ceil(number * round) / round;
  }
}
