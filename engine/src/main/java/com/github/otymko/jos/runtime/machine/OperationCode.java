/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package com.github.otymko.jos.runtime.machine;

/**
 * Опкоды. Взято из ScriptEngine.Machine
 */
public enum OperationCode {
    Nop,
    PushVar,
    PushConst,
    PushLoc,
    PushRef,
    LoadVar,
    LoadLoc,
    AssignRef,
    Add,
    Sub,
    Mul,
    Div,
    Mod,
    Neg,
    Equals,
    Less,
    Greater,
    LessOrEqual,
    GreaterOrEqual,
    NotEqual,
    Not,
    And,
    Or,
    CallFunc,
    CallProc,
    ArgNum,
    PushDefaultArg,
    ResolveProp,
    ResolveMethodProc,
    ResolveMethodFunc,
    Jmp,
    JmpFalse,
    PushIndexed,
    Return,
    JmpCounter,
    Inc,
    NewInstance,
    PushIterator,
    IteratorNext,
    StopIterator,
    BeginTry,
    EndTry,
    RaiseException,
    LineNum,
    MakeRawValue,
    MakeBool,
    PushTmp,
    PopTmp,
    Execute,
    AddHandler,
    RemoveHandler,
    ExitTry,

    // built-in functions
    Eval,
    Bool,
    Number,
    Str,
    Date,
    Type,
    ValType,
    StrLen,
    TrimL,
    TrimR,
    TrimLR,
    Left,
    Right,
    Mid,
    StrPos,
    UCase,
    LCase,
    TCase,
    Chr,
    ChrCode,
    EmptyStr,
    StrReplace,
    StrGetLine,
    StrLineCount,
    StrEntryCount,
    Year,
    Month,
    Day,
    Hour,
    Minute,
    Second,
    BegOfYear,
    BegOfMonth,
    BegOfDay,
    BegOfHour,
    BegOfMinute,
    BegOfQuarter,
    EndOfYear,
    EndOfMonth,
    EndOfDay,
    EndOfHour,
    EndOfMinute,
    EndOfQuarter,
    WeekOfYear,
    DayOfYear,
    DayOfWeek,
    AddMonth,
    CurrentDate,
    Integer,
    Round,
    Log,
    Log10,
    Sin,
    Cos,
    Tan,
    ASin,
    ACos,
    ATan,
    Exp,
    Pow,
    Sqrt,
    Min,
    Max,
    Format,
    ExceptionInfo,
    ExceptionDescr,
    ModuleInfo;
}
