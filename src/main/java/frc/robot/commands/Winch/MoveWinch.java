// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Winch;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.WinchSubsystem;

public class MoveWinch extends Command {

  WinchSubsystem winchSub;
  double speed;

  /** Creates a new MoveWinch. */
  public MoveWinch(WinchSubsystem winchSub, double speed) {
    this.winchSub = winchSub;
    this.speed = speed;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(winchSub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
   
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
     winchSub.setMotor(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    winchSub.setMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
