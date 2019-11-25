/*
 * IdeaVim - Vim emulator for IDEs based on the IntelliJ platform
 * Copyright (C) 2003-2019 The IdeaVim authors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package com.maddyhome.idea.vim.action.change.delete;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import com.maddyhome.idea.vim.VimPlugin;
import com.maddyhome.idea.vim.command.Command;
import com.maddyhome.idea.vim.command.CommandFlags;
import com.maddyhome.idea.vim.command.SelectionType;
import com.maddyhome.idea.vim.group.visual.VimSelection;
import com.maddyhome.idea.vim.handler.VisualOperatorActionHandler;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;

/**
 * @author vlan
 */
final public class DeleteVisualAction extends VisualOperatorActionHandler.ForEachCaret {

  @Contract(pure = true)
  @NotNull
  @Override
  final public Command.Type getType() {
    return Command.Type.DELETE;
  }

  @NotNull
  @Override
  final public EnumSet<CommandFlags> getFlags() {
    return EnumSet.of(CommandFlags.FLAG_EXIT_VISUAL);
  }

  @Override
  public boolean executeAction(@NotNull Editor editor,
                               @NotNull Caret caret,
                               @NotNull DataContext context,
                               @NotNull Command cmd,
                               @NotNull VimSelection range) {
    SelectionType selectionType = range.getType();
    return VimPlugin.getChange().deleteRange(editor, caret, range.toVimTextRange(false), selectionType, false);
  }
}