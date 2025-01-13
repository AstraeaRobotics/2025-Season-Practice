// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Winch;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants.WinchConstants.WinchStates;
import frc.robot.subsystems.WinchSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class SetWinchState extends InstantCommand {
  WinchSubsystem winchSub;
  WinchStates state;

  public SetWinchState(WinchSubsystem winchSub, WinchStates state) {
    this.winchSub = winchSub;
    this.state = state;
    addRequirements(winchSub);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    winchSub.setWinchState(state);
  }
}
