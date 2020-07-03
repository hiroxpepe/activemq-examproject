/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.examproject.service;

import java.util.HashMap;
import java.util.Map;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.examproject.repository.OrderRepository;

/**
 * @author h.adachi
 */
@Slf4j
@RequiredArgsConstructor
@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService {

    ///////////////////////////////////////////////////////////////////////////
    // Fields

    @NonNull
    private final OrderRepository orderRepository;

    ///////////////////////////////////////////////////////////////////////////
    // public Methods

    @Override
    public Map<String, Long> getOrderCountMap() {
        Map<String, Long> countNameMap = new HashMap<>();
        orderRepository.sumQuantityGroupByProductName().stream().map(o -> (Object[]) o)
            .forEachOrdered((array) -> {
                countNameMap.put((String) array[1], (Long) array[0]);
            });
        return countNameMap;
    }

}
