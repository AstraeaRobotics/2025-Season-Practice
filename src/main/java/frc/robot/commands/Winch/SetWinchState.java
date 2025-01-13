// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Winch;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants.WinchConstants.WinchStates;
import frc.robot.subsystems.ElevatorSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class SetWinchState extends InstantCommand {
  ElevatorSubsystem elevatorSub;
  WinchStates state;
  
  public SetWinchState(ElevatorSubsystem elevatorSub, WinchStates state) {
    this.elevatorSub = elevatorSub;
    this.state = state;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(elevatorSub);
  }

// Called when the command is initially scheduled.
  @Override
  public void initialize() {
    elevatorSub.setState(state);
  }
}