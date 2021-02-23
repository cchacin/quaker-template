#!/usr/bin/env bash

for CNN in 1 5; do
    for THREADS in {1..2}; do
        echo "Running $CNN connections with $THREADS threads for 60 seconds"
        wrk --threads=$THREADS --connections=$CNN --latency -d60s http://localhost:8080/todos
    done
done
