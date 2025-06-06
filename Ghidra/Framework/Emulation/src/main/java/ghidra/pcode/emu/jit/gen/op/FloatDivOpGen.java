/* ###
 * IP: GHIDRA
 *
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
package ghidra.pcode.emu.jit.gen.op;

import static ghidra.lifecycle.Unfinished.TODO;

import org.objectweb.asm.MethodVisitor;

import ghidra.pcode.emu.jit.analysis.JitControlFlowModel.JitBlock;
import ghidra.pcode.emu.jit.analysis.JitType;
import ghidra.pcode.emu.jit.analysis.JitType.*;
import ghidra.pcode.emu.jit.gen.JitCodeGenerator;
import ghidra.pcode.emu.jit.op.JitFloatDivOp;

/**
 * The generator for a {@link JitFloatDivOp float_div}.
 * 
 * <p>
 * This uses the binary operator generator and simply emits {@link #FDIV} or {@link #DDIV} depending
 * on the type.
 */
public enum FloatDivOpGen implements BinOpGen<JitFloatDivOp> {
	/** The generator singleton */
	GEN;

	@Override
	public JitType generateBinOpRunCode(JitCodeGenerator gen, JitFloatDivOp op, JitBlock block,
			JitType lType, JitType rType, MethodVisitor rv) {
		assert rType == lType;
		switch (lType) {
			case FloatJitType t -> rv.visitInsn(FDIV);
			case DoubleJitType t -> rv.visitInsn(DDIV);
			case MpFloatJitType t -> TODO("MpFloat");
			default -> throw new AssertionError();
		}
		return lType;
	}
}
