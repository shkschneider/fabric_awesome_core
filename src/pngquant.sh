#!/usr/bin/env sh

for file in $(find */src -type f -name *.png) ; do
  pngquant --skip-if-larger --force "$file" --output "$file"
done

# EOF
