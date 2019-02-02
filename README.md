# mine-bot

A Discord bot for generating minesweeper boards. Depends on
[`atamis-discord.clj`](https://github.com/atamis/discord.clj), a forked variant
of [`discord.clj`](https://github.com/gizmo385/discord.clj).

## Installation

Clone this repo.

## Usage

Copy `data/settings/settings.json.template` to a new file and update it with
your token and name.

    $ lein run

## Options

See `data/settings/settings.json.template` for all availablle configuration
options, except for the prefix, which this bot ignores.

## License

Copyright © 2019 Andrew Amis

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
